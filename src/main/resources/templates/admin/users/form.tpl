layout 'layout/main.tpl', title:  'User',
content: contents {
  h4 ('User')
  form (class:'col s12', method:'post') {
    input (type:'hidden', name:_csrf.parameterName, value:_csrf.token)
    div (class:'row') {
      div (class:'input-field col s4') {
        if (errors && errors.hasFieldErrors('firstName')) {
          input (id:'firstName', name:'firstName', type:'text', class:'validate invalid', value:errors.getFieldValue('firstName'))
          label (for:'firstName', 'data-error':errors.getFieldErrors('firstName')*.getDefaultMessage().join(', '), 'FirstName')
        } else {
          input (id:'firstName', name:'firstName', type:'text', class:'validate', value:user.getFirstName())
          label (for:'firstName', 'FirstName')
        }
      }
      div (class:'input-field col s4') {
        if (errors && errors.hasFieldErrors('lastName')) {
          input (id:'lastName', name:'lastName', type:'text', class:'validate invalid', value:errors.getFieldValue('lastName'))
          label (for:'lastName', 'data-error':errors.getFieldErrors('lastName')*.getDefaultMessage().join(', '), 'LastName')
        } else {
          input (id:'lastName', name:'lastName', type:'text', class:'validate', value:user.getLastName())
          label (for:'lastName', 'LastName')
        }
      }
      div (class:'input-field col s4') {
        if (errors && errors.hasFieldErrors('surname')) {
          input (id:'surname', name:'surname', type:'text', class:'validate invalid', value:errors.getFieldValue('surname'))
          label (for:'surname', 'data-error':errors.getFieldErrors('surname')*.getDefaultMessage().join(', '), 'Surname')
        } else {
          input (id:'surname', name:'surname', type:'text', class:'validate', value:user.getSurname())
          label (for:'surname', 'surname')
        }
      }
    }
    div (class:'row') {
      div (class:'input-field col s12') {
        if (errors && errors.hasFieldErrors('email')) {
          input (id:'email', name:'email', type:'email', class:'validate invalid', value:errors.getFieldValue('email'))
          label (for:'email', 'data-error':errors.getFieldErrors('email')*.getDefaultMessage().join(', '), 'Email')
        } else {
          input (id:'email', name:'email', type:'email', class:'validate', value:user.getEmail())
          label (for:'email', 'email')
        }
      }
    }
    div (class:'row') {
      div (class:'input-field col s6') {
        if (errors && errors.hasFieldErrors('password')) {
          input (id:'password', name:'password', type:'password', class:'validate invalid', value:errors.getFieldValue('password'))
          label (for:'password', 'data-error':errors.getFieldErrors('password')*.getDefaultMessage().join(', '), 'Password')
        } else {
          input (id:'password', name:'password', type:'password', class:'validate', value:user.getPassword())
          label (for:'password', 'password')
        }
      }
      div (class:'input-field col s6') {
        if (errors && errors.hasFieldErrors('passwordRepeat')) {
          input (id:'passwordRepeat', name:'passwordRepeat', type:'password', class:'validate invalid', value:errors.getFieldValue('passwordRepeat'))
          label (for:'passwordRepeat', 'data-error':errors.getFieldErrors('passwordRepeat')*.getDefaultMessage().join(', '), 'Password Repeat')
        } else {
          input (id:'passwordRepeat', name:'passwordRepeat', type:'password', class:'validate', value:user.getPasswordRepeat())
          label (for:'passwordRepeat', 'passwordRepeat')
        }
      }
    }
    div (class:'row') {
      div (class:'input-field col s8') {
        select (id:'state', name:'state') {
          if ((errors && errors.hasFieldErrors('state')) || user.getState().equals(3)) {
              option (value:'3', selected:'selected', 'Active')
          } else {
              option (value:'3', 'Active')
          }
          if (user.getState().equals(4)) {
              option (value:'4', selected:'selected', 'Disabled')
          } else {
              option (value:'4', 'Disabled')
          }
        }
        if (errors && errors.hasFieldErrors('state')) {
          label (for:'state', 'data-error':errors.getFieldErrors('state')*.getDefaultMessage().join(', '), 'State')
        } else {
          label (for:'state', 'State')
        }
      }
      div (class:'input-field col s4') {
        if (errors && errors.hasFieldErrors('isAdmin')) {
          input (id:'isAdmin', name:'isAdmin', class:'validate invalid', type:'checkbox')
          label (for:'isAdmin', 'data-error':errors.getFieldErrors('isAdmin')*.getDefaultMessage().join(', '), 'isAdmin')
        } else {
          if (user.getIsAdmin()) {
            input (id:'isAdmin', name:'isAdmin', class:'validate', type:'checkbox', checked:'checked')
          } else {
            input (id:'isAdmin', name:'isAdmin', class:'validate', type:'checkbox')
          }
          label (for:'isAdmin', 'isAdmin')
        }
      }
    }
    div (class:'row') {
      div (class:'col s12') {
        button (class:'waves-effect waves-light btn-large blue', type:'submit') {
          i (class:'material-icons left', 'save')
          if (update) {
            yield 'Update'
          } else if (create) {
            yield 'Create'
          }
        }
        a (class:'waves-effect waves-light btn-large red', href:usersListUrl) {
          i (class:'material-icons left', 'cancel')
          yield 'Cancel'
        }
      }
    }
  }
}
