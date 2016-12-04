$.validator.setDefaults({
    errorClass: 'invalid',
    errorPlacement: function (error, element) {
        element.next("label").attr("data-error", error.contents().text());
    }
});
$('form').validate();