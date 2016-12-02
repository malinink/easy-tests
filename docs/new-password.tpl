yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
  head {
    meta(charset:'UTF-8')
    title('New Password')
    link(href:'http://fonts.googleapis.com/icon?family=Material+Icons', rel:'stylesheet')
    link(type:'text/css', rel:'stylesheet', href:'/css/materialize.css', media:'screen,projection')
    meta(name:'viewport', content:'width=device-width, initial-scale=1.0')
  }
  body {
    header(class:'container') {
      h1('Header')
    }

    main(class:'container') {
      div(class:'row') {
        form(class:'col s12', action:'#', method:'post') {
          div(class:'row') {
            div(class:'input-field col s6') {
              i(class:'material-icons prefix', 'email')
              input(id:'password', type:'password', class:'validate')
              label(for:'password') {
                yield 'Ваш новый пароль'
              }
            }
          }
          div(class:'row') {
            div(class:'col s6') {
              button(class:'btn waves-effect waves-light', type:'submit', name:'action') {
                yield 'Изменить'
                i(class:'material-icons right', 'send')
              }
            }
          }
        }
      }
    }

    footer(class:'container') {
      p('Footer')
    }

    script(type:'text/javascript', src:'js/script.js') {}
    script(type:'text/javascript', src:'js/jquery.js') {}
    script(type:'text/javascript', src:'js/materialize.js') {}
  }
}