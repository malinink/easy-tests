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