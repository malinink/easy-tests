$.validator.setDefaults({
    errorClass: 'invalid',
    errorPlacement: function (error, element) {
        element.next("label").attr("data-error", error.contents().text());
    }
});
$('form').validate();

formRecovery = document.getElementById("recovery-password");
message = document.querySelector(".submit-message");

formRecovery.addEventListener("submit", function (event) {
    event.preventDefault();
    formRecovery.classList.add("hide-block");
    message.classList.remove("hide-block");
});

$(document).ready(function () {
    $('select').material_select();
});