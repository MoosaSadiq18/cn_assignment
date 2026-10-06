const API_URL = "/api/opportunities";

const form = document.getElementById("opportunity-form");

const message = document.getElementById("message");

const opportunitiesContainer =
    document.getElementById("opportunities");


document.addEventListener("DOMContentLoaded", loadOpportunities);


form.addEventListener("submit", async function (event) {

    event.preventDefault();

    const id = document.getElementById("opportunity-id").value;

    const opportunity = {

        researchTitle:
        document.getElementById("researchTitle").value,

        researchDescription:
        document.getElementById("researchDescription").value,

        researchArea:
        document.getElementById("researchArea").value,

        facultyName:
        document.getElementById("facultyName").value,

        department:
        document.getElementById("department").value,

        requiredSkills:
        document.getElementById("requiredSkills").value,

        availablePositions:
            Number(document.getElementById("availablePositions").value),

        applicationDeadline:
        document.getElementById("applicationDeadline").value,

        status:
        document.getElementById("status").value
    };

    try {

        let response;

        if (id) {

            response = await fetch(`${API_URL}/${id}`, {
                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(opportunity)
            });

        } else {

            response = await fetch(API_URL, {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(opportunity)
            });
        }

        if (!response.ok) {

            const error = await response.json();

            throw new Error(
                Object.values(error).join(", ")
            );
        }

        showMessage(
            id
                ? "Opportunity updated successfully."
                : "Opportunity created successfully.",
            "success"
        );

        resetForm();

        loadOpportunities();

    } catch (error) {

        showMessage(
            error.message,
            "error"
        );
    }
});


async function loadOpportunities() {

    try {

        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Failed to load opportunities.");
        }

        const opportunities = await response.json();

        displayOpportunities(opportunities);

    } catch (error) {

        showMessage(
            error.message,
            "error"
        );
    }
}


function displayOpportunities(opportunities) {

    opportunitiesContainer.innerHTML = "";

    if (opportunities.length === 0) {

        opportunitiesContainer.innerHTML =
            "<p>No research opportunities found.</p>";

        return;
    }

    opportunities.forEach(opportunity => {

        const card = document.createElement("div");

        card.className = "card";

        card.innerHTML = `

            <h3>${escapeHtml(opportunity.researchTitle)}</h3>

            <p>
                <strong>Area:</strong>
                ${escapeHtml(opportunity.researchArea)}
            </p>

            <p>
                <strong>Faculty:</strong>
                ${escapeHtml(opportunity.facultyName)}
            </p>

            <p>
                <strong>Department:</strong>
                ${escapeHtml(opportunity.department)}
            </p>

            <p>
                <strong>Positions:</strong>
                ${opportunity.availablePositions}
            </p>

            <p>
                <strong>Deadline:</strong>
                ${opportunity.applicationDeadline}
            </p>

            <p>
                <strong>Status:</strong>
                ${opportunity.status}
            </p>

            <div class="buttons">

                <button onclick="viewOpportunity(${opportunity.id})">
                    View
                </button>

                <button onclick="editOpportunity(${opportunity.id})">
                    Edit
                </button>

                <button onclick="closeOpportunity(${opportunity.id})">
                    Close
                </button>

                <button
                    class="delete"
                    onclick="deleteOpportunity(${opportunity.id})"
                >
                    Delete
                </button>

            </div>
        `;

        opportunitiesContainer.appendChild(card);
    });
}


async function viewOpportunity(id) {

    try {

        const response =
            await fetch(`${API_URL}/${id}`);

        if (!response.ok) {
            throw new Error("Opportunity not found.");
        }

        const opportunity = await response.json();

        alert(`
Title: ${opportunity.researchTitle}

Description:
${opportunity.researchDescription}

Research Area:
${opportunity.researchArea}

Faculty:
${opportunity.facultyName}

Department:
${opportunity.department}

Required Skills:
${opportunity.requiredSkills}

Available Positions:
${opportunity.availablePositions}

Application Deadline:
${opportunity.applicationDeadline}

Status:
${opportunity.status}
        `);

    } catch (error) {

        showMessage(
            error.message,
            "error"
        );
    }
}


async function editOpportunity(id) {

    try {

        const response =
            await fetch(`${API_URL}/${id}`);

        if (!response.ok) {
            throw new Error("Opportunity not found.");
        }

        const opportunity = await response.json();

        document.getElementById("opportunity-id").value =
            opportunity.id;

        document.getElementById("researchTitle").value =
            opportunity.researchTitle;

        document.getElementById("researchDescription").value =
            opportunity.researchDescription;

        document.getElementById("researchArea").value =
            opportunity.researchArea;

        document.getElementById("facultyName").value =
            opportunity.facultyName;

        document.getElementById("department").value =
            opportunity.department;

        document.getElementById("requiredSkills").value =
            opportunity.requiredSkills;

        document.getElementById("availablePositions").value =
            opportunity.availablePositions;

        document.getElementById("applicationDeadline").value =
            opportunity.applicationDeadline;

        document.getElementById("status").value =
            opportunity.status;

        document.getElementById("form-title").textContent =
            "Update Opportunity";

        document.getElementById("cancel-button").style.display =
            "inline-block";

        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });

    } catch (error) {

        showMessage(
            error.message,
            "error"
        );
    }
}


async function closeOpportunity(id) {

    try {

        const response =
            await fetch(`${API_URL}/${id}`);

        if (!response.ok) {
            throw new Error("Opportunity not found.");
        }

        const opportunity = await response.json();

        opportunity.status = "CLOSED";

        const updateResponse =
            await fetch(`${API_URL}/${id}`, {

                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(opportunity)
            });

        if (!updateResponse.ok) {
            throw new Error("Failed to close opportunity.");
        }

        showMessage(
            "Opportunity closed successfully.",
            "success"
        );

        loadOpportunities();

    } catch (error) {

        showMessage(
            error.message,
            "error"
        );
    }
}


async function deleteOpportunity(id) {

    if (!confirm("Delete this opportunity?")) {
        return;
    }

    try {

        const response =
            await fetch(`${API_URL}/${id}`, {
                method: "DELETE"
            });

        if (!response.ok) {
            throw new Error("Failed to delete opportunity.");
        }

        showMessage(
            "Opportunity deleted successfully.",
            "success"
        );

        loadOpportunities();

    } catch (error) {

        showMessage(
            error.message,
            "error"
        );
    }
}


function resetForm() {

    form.reset();

    document.getElementById("opportunity-id").value = "";

    document.getElementById("form-title").textContent =
        "Create Opportunity";

    document.getElementById("cancel-button").style.display =
        "none";
}


function showMessage(text, type) {

    message.textContent = text;

    message.className = type;

    setTimeout(() => {

        message.textContent = "";
        message.className = "";

    }, 4000);
}


function escapeHtml(value) {

    const div = document.createElement("div");

    div.textContent = value;

    return div.innerHTML;
}