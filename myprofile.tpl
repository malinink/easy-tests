yieldUnescaped '<!DOCTYPE html>'
html(lang:'ru') {
  
  head {
    meta(charset:'UTF-8')
    title('Личный кабинет')
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
        div(class:'row'){
          div(class:'input-field col s6'){
            input(disabled, value:'vasyapupkin@gmail.com', id:'email_disabled', type:'text', class:'validate')
            label(for:'email_disabled'){
              yield 'Почтовый адрес'
            }
          }
          div(class:'inpit-field col s6'){
            a(class:'waves-effect waves-teal btn-flat','Изменить')
          }
        }
        div(class:'row'){
          div(class:'input-field col s6'){
            input(id:'new_password', type:'password', class:'validate')
            label(for:'new_password'){
              yield:'Новый пароль'
            }
          }
        }
        div(class:'row'){
          div(class:'input-field col s6'){
            input(id:'retype_password', type:'password', class:'validate')
            label(for:'retype_password'){
              yield:'Подтвердите пароль'
            }
          }
        }
        div(class:'row'){
          div(class:'input-field col s6'){
            input(id:'old_password', type:'password', class:'validate')
            label(for:'old_password'){
              yield:'Ваш текущий пароль'
            }
          }
        }
        div(class:'row'){
          button(class:'btn waves-effect waves-light', type:'submit', name:'action'){
            yield:'Подтвердить'
          }
          i(class:'material-icons right','send')
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