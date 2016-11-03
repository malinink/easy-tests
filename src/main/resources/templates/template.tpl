layout 'layout/layout-main.tpl',
title: 'Layout example',
 content: contents {
        div (style: 'margin: 40px auto; float: none; display: table;')
        {
              table(style: 'padding: 5px; border: 2px solid #000; width: 520px; border-collapse: collapse;')
              {
                thead
                {
                  tr (style: 'border: 2px solid #000; padding: 10px; background: #e2e2e2; height: 30px;')
                  {
                    td 'First name'
                    td 'Last name'
                    td 'Surname'
                  }
                }
                tbody
                {
                    if (Users.empty) { tr { td(colspan:'3', 'No Users' ) } }
                    Users.each { User ->
                        tr (style: 'border: 2px solid #000; padding: 10px;')
                        {
                          td "${User.getFirstName()}"
                          td "${User.getLastName()}"
                          td "${User.getSurname()}"
                        }
                    }
                }
              }
        }
 }
