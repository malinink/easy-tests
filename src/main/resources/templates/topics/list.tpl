layout 'layout/main.tpl', title:  'Topics',
content: contents {
  h4 ('Topics')
  a (class:'right waves-effect waves-light btn-floating btn-large blue', href:'/personal/subjects/' + subjectId + '/topics/create/') {
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
      def id = 0
      topics.each { topic ->
        id++
        tr {
          td (id)
          td (topic.name)
          td() {
            a (class:'waves-effect waves-light btn-floating blue', href:'/personal/subjects/' + subjectId +'/topics/' + topic.id + '/') {
              i (class:'material-icons left', 'pageview')
              yield 'View'
            }
            a (class:'waves-effect waves-light btn-floating blue', href:'/personal/subjects/' + subjectId +'/topics/update/' + topic.id + '/') {
              i (class:'material-icons left', 'edit')
              yield 'Edit'
            }
            a (class:'waves-effect waves-light btn-floating red', href:'/personal/subjects/' + subjectId +'/topics/delete/' + topic.id + '/') {
              i (class:'material-icons left', 'delete')
              yield 'Delete'
            }
          }
        }
      }
    }
  }
}
