yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
  head {
    meta(charset:'UTF-8')
    title('Password is changed')
    link(href:'http://fonts.googleapis.com/icon?family=Material+Icons', rel:'stylesheet')
    link(type:'text/css', rel:'stylesheet', href:'/css/materialize.css', media:'screen,projection')
    meta(name:'viewport', content:'width=device-width, initial-scale=1.0')
  }
  body {
    header(class:'container') {
      h1('Header')
    }

    main(class:'container') {
      div(class:'flow-text card-panel center-align') {
        span(class:'green-text text-darken-2') {
          i(class:'material-icons', 'done')
          yield ' Пароль успешно изменён!'
        }
      }
    }

    footer(class:'container') {
      p('Footer')
    }

    script(type:'text/javascript', src:'js/jquery.js') {}
    script(type:'text/javascript', src:'js/materialize.js') {}
  }
} 