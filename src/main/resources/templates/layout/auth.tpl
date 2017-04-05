yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    title(title)
    link(href:'https://fonts.googleapis.com/icon?family=Material+Icons', rel:'stylesheet', media:'screen, projection')
    link(type:'text/css', rel:'stylesheet', href:'/css/materialize.css', media:'screen, projection')
    link(type:'text/css', rel:'stylesheet', href:'/css/style.css', media:'screen, projection')
  }
  body {
    content()
    script(type:'text/javascript', src:'/js/jquery.js') {}
    script(type:'text/javascript', src:'/js/materialize.js') {}
    script(src:'https://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js') {}
    script(type:'text/javascript', src:'/js/script.js') {}
  }
}
