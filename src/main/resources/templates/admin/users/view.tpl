layout 'layout/main.tpl', title:  'User',
content: contents {
  h4 ('User')
  div (class:'row') {
    div (class:'input-field col s4') {
      label ('FirstName')
      p (user.firstName)
    }
    div (class:'input-field col s4') {
      label ('LastName')
      p (user.lastName)
    }
    div (class:'input-field col s4') {
      label ('surname')
      p (user.surname)
    }
  }
  div (class:'row') {
    div (class:'input-field col s12') {
      label ('Email')
      p (user.email)
    }
  }
  div (class:'row') {
    div (class:'input-field col s8') {
      label ('State')
      p (user.state)
    }
    div (class:'input-field col s8') {
      label ('IsAdmin')
      p (user.isAdmin)
    }
  }
  div (class:'row') {
    div (class:'col s12') {
      a (class:'waves-effect waves-light btn-large blue', href:usersListUrl) {
        i (class:'material-icons left', 'back')
        yield 'Back'
      }
    }
  }
}
