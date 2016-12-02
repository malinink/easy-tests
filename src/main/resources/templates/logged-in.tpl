layout 'layout/main.tpl', title:'Main page',
content: contents {
    nav(class: 'light-blue lighten-1', role: 'navigation') {
     a (id: 'logo-container', href: '#', class:'brand-logo'){
     yield 'EasyTests'
    }
     div(class: 'nav-wrapper container') {
          ul(class: 'right hide-on-med-and-down'){
            li {
                yield 'Добро пожаловать, User!'
            }
            li {
              a(href: '/profile') {
                yield 'Мой Кабинет'
              }
            }
            li {
              a(href: '#') {
                yield 'Выход'
              }
            }
          }
        }

        div(class: 'container'){
         yield 'Main page content'
        }

        footer(class: 'page-footer orange'){
          div(class: 'footer-copyright'){
              a(class: 'orange-text text-lighten-3', href: 'https://github.com/malinink/easy-tests'){
                yield 'GitHub repository'
            }
          }
    }
}