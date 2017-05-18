layout 'layout/auth.tpl', title:  'Login form',
content: contents {
    div (class:'section') {
        main {
            div(class :'center-align') {
                div (class:'section') {
                    h5 {
                        span (sign_in)
                    }
                }
                div (class:'container') {
                    div (id: 'block', class:'z-depth-1 grey lighten-4 row') {
                        form (class:'col s12', method:'post') {
                            input (type:'hidden', name:_csrf.parameterName, value:_csrf.token)
                            div (class:'row') {
                                div (class: 'col s12') {}
                            }
                            div (class:'row') {
                                div (class:'input-field col s12') {
                                    i (class:'material-icons prefix') {
                                        span('account_circle')
                                    }
                                    input (class:'validate', type:'email', name:'login', id:'login', required:'', value:login)
                                    label (for:'email') {
                                        span (enter_email)
                                    }
                                }
                            }
                            div (class: 'row') {
                                div (class:'input-field col s12') {
                                    i (class:'material-icons prefix') {
                                        span('lock')
                                    }
                                    input (class:'validate', type:'password', name:'password', id:'password', required:'')
                                    label (for:'password') {
                                        span (enter_password)
                                    }
                                }
                                label (style:'float: right;') {
                                    a (class: 'grey-text', href:'#!') {
                                        b (forgot_password)
                                    }
                                }
                            }
                            br
                            div(class :'center-align') {
                                div (class:'row') {
                                    button (type:'submit', name:'btn_login', class:'col s12 btn btn-large waves-effect waves-light teal lighten-2') {
                                        span (login_text)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
