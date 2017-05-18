layout 'layout/main.tpl', title:  'Topic',
content: contents {
  if (update) {
    h4 ('Update')
  } else if (create) {
    h4 ('Create')
  }
  form (class:'col s12', method:'post') {
    input (type:'hidden', name:_csrf.parameterName, value:_csrf.token)
    div (class:'row') {
      div (class:'input-field col s12') {
        if (errors && errors.hasFieldErrors('name')) {
          textarea (id:'topic_name', name: 'name', class:'materialize-textarea validate invalid', value:errors.getFieldValue('name')) {}
          label (for:'topic_name', 'data-error':errors.getFieldErrors('name')*.getDefaultMessage().join(', '), 'name')
        } else {
          textarea (id:'topic_name', name: 'name', class:'materialize-textarea') {
            if (update)
            {
              yield topic.name
            }
          }
          label (for:'topic_name', class: 'grey-text', 'Topic')
        }
      }
    }
    div (class:'row') {
      div (class:'col s12') {
        button (class:'waves-effect waves-light btn-large blue', 'type':'submit', 'name':'save') {
          i (class:'material-icons left', 'save')
          yield 'Save'
        }
        a (class:'waves-effect waves-light btn-large red', href:'/personal/subjects/' + subjectId + '/topics/') {
          i (class:'material-icons left', 'cancel')
          yield 'Cancel'
        }
      }
    }
  }
}
