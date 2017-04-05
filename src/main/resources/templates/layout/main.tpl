yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    title(title)
    link(type:'text/css', rel:'stylesheet', href:'/css/materialize.css', media:'screen, projection')
  }
  body {
    form (class:'col s12', method:'post', action:'/auth/sign-out') {
      div(class :'center-align') {
        div (class:'row') {
          button (type:'submit', class:'col s2 btn btn-large waves-effect waves-light teal lighten-2') {
            span ('Sign out')
          }
        }
      }
    }
    content()
    script(type:'text/javascript', src:'/js/jquery.js') {}
    script(type:'text/javascript', src:'/js/materialize.js') {}
  }
}
