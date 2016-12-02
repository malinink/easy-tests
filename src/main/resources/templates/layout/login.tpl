yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    title(title)
    link(href:'https://fonts.googleapis.com/icon?family=Material+Icons', rel:'stylesheet', media:'screen, projection')
    link(type:'text/css', rel:'stylesheet', href:'/css/materialize.css', media:'screen, projection')
    style('label:after {transition-property: all !important;font-size: 0.8rem;transform: none;}label:not(.active):after {transform: translateY(-140%);}')
  }
  body (style: 'display: flex; min-height: 100vh; flex-direction: column;') {
    content()
    script(type:'text/javascript', src:'/js/jquery.js') {}
    script(type:'text/javascript', src:'/js/materialize.js') {}
    script(src:'https://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js') {}
    script('$.validator.setDefaults({errorClass: "invalid",errorPlacement: function (error, element) {element.next("label").attr("data-error", error.contents().text());}});$("form").validate();')
  }
}
