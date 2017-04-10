layout 'layout/main.tpl', title:  'Layout example',
content: contents {
  h4 ('Table layout example')
  a (class:'right waves-effect waves-light btn-floating btn-large blue') {
    i (class:'material-icons left', 'add')
    yield 'Add'
  }
  table(class: 'striped') {
    thead {
      tr {
        th('#')
        th('Name')
        th('Action')
      }
    }
    tbody {
      tr {
        td('Alvin')
        td('Eclair')
        td() {
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
      tr {
        td('Alvin')
        td('Eclair')
        td() {
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
  h4 ('Form layout example')
  form (class:'col s12') {
    div (class:'row') {
      div (class:'input-field col s12') {
        input (id:'field_name', type:'text', class:'validate')
        label (for:'field_name', 'Field')
      }
    }
    div (class:'row') {
      div (class:'input-field col s12') {
        textarea (id:'textarea_name', class:'materialize-textarea') {}
        label (for:'textarea_name', 'Textarea')
      }
    }
    div (class:'row') {
      div (class:'input-field col s12') {
        select (id:'select_name') {
          option ('Choose your option', value:'', disabled:'disabled', selected:'selected')
          option ('Option 1', value:'1')
          option ('Option 2', value:'2')
          option ('Option 3', value:'3')
        }
        label (for:'select_name', 'Select')
      }
    }
    div (class:'row') {
      div (class:'col s12') {
        a (class:'waves-effect waves-light btn-large blue') {
          i (class:'material-icons left', 'save')
          yield 'Save'
        }
        a (class:'waves-effect waves-light btn-large red') {
          i (class:'material-icons left', 'cancel')
          yield 'Cancel'
        }
      }
    }
  }
}