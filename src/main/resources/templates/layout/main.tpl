yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    title(title)
    link(rel:'stylesheet', media:'screen, projection', href:'https://fonts.googleapis.com/icon?family=Material+Icons')
    link(type:'text/css', rel:'stylesheet', href:'/css/materialize.css', media:'screen, projection')
    link(type:'text/css', rel:'stylesheet', href:'/css/style.css', media:'screen, projection')
  }
  body {
    if (_csrf) {
      form (class:'col s12', method:'post', action:'/auth/sign-out') {
        input (type:'hidden', name:_csrf.parameterName, value:_csrf.token)
        div(class :'center-align') {
          div (class:'row') {
            button (type:'submit', class:'col s2 btn btn-large waves-effect waves-light teal lighten-2') {
              span ('Sign out')
            }
          }
        }
      }
    }
    div (class:'container') {
      content()
    }
    script(type:'text/javascript', src:'/js/jquery.js') {}
    script(type:'text/javascript', src:'/js/materialize.js') {}
    script(type:'text/javascript', src:'/js/script.js') {}
    script(type:'text/javascript', src:'https://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js') {}
  }
}
