yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
  head {
    meta(charset:'UTF-8')
    title('Recovery password')
    link(href:'http://fonts.googleapis.com/icon?family=Material+Icons', rel:'stylesheet')
    link(type:'text/css', rel:'stylesheet', href:'/css/materialize.css', media:'screen,projection')
    meta(name:'viewport', content:'width=device-width, initial-scale=1.0')
  }
  body {
    header(class:'container') {
      h1('Header')
    }

    main(class:'container') {
      div(id:'recovery-password', class:'row') {
        form(class:'col s12') {
          div(class:'row') {
            p(class:'col s12') {
              yield 'Для восстановления пароля введите ваш e-mail'
            }
            div(class:'input-field col s6') {
              i(class:'material-icons prefix', 'email')
              input(id:'email', type:'email', class:'validate')
              label(for:'email') {
                yield 'Ваш e-mail'
              }
            }
          }
          div(class:'row') {
            div(class:'col s6') {
              button(class:'btn waves-effect waves-light', type:'submit', name:'action') {
                yield 'Отправить'
                i(class:'material-icons right', 'send')
              }
            }
          }
        }
      }
      div(class:'flow-text card-panel center-align submit-message hide-block col s12') {
        span(class:'green-text text-darken-2') {
          i(class:'material-icons', 'done')
          yield 'Письмо для восстановления пароля отправлено на адрес вашей электронной почты'
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