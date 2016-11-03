yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    title('Students Table')
    link(type: 'text/css', rel: 'stylesheet', href: '/css/materialize.css', media: 'screen,projection')
  }
  body {
    div(class: 'container') {
      h3('307 группа')
      table(class: 'striped') {
        thead {
          tr {
            th('Name')
            th('Last name')
            th('Surname')
          }
        }
        tbody {
          usersList.each { user ->
            tr {
              td(user.getFirstName())
              td(user.getLastName())
              td(user.getSurname())
            }
          }
        }
      }
    }

  }
}