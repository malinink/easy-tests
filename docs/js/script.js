formRecovery = document.getElementById("recovery-password");
message = document.querySelector(".submit-message");

formRecovery.addEventListener("submit", function(event) {
    event.preventDefault();
    formRecovery.classList.add("hide-block");
    message.classList.remove("hide-block");
});