layout 'layout/main.tpl', title:  'Users',
content: contents {
  h4 ('Users')
  a (class:'right waves-effect waves-light btn-floating btn-large blue', href:'/admin/users/create/') {
    i (class:'material-icons left', 'add')
    yield 'Add'
  }
  table(class: 'striped') {
    thead {
      tr {
        th ('#')
        th ('Firstname')
        th ('Lastname')
        th ('Action')
      }
    }
    tbody {
      def id = 0
      users.each { user ->
        id++
        tr {
          td (id)
          td (user.getFirstName())
          td (user.getLastName())
          td {
            a (class:'waves-effect waves-light btn-floating blue') {
              i (class:'material-icons left', 'pageview')
              yield 'View'
            }
            a (class:'waves-effect waves-light btn-floating blue') {
              i (class:'material-icons left', 'edit')
              yield 'Edit'
            }
            a (class:'waves-effect waves-light btn-floating red') {
              i (class:'material-icons left', 'delete')
              yield 'Delete'
            }
          }
        }
      }
    }
  }
}