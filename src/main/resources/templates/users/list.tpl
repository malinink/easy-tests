layout 'layout/main.tpl', title:  'Layout example',
content: contents {
  table(class: 'striped') {
    thead {
      tr {
        th('Name')
        th('Last name')
        th('Surname')
      }
    }
    tbody {
      users.each { user ->
        tr {
          td(user.getFirstName())
          td(user.getLastName())
          td(user.getSurname())
        }
      }
    }
  }
}