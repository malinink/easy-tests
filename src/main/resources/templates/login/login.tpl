layout 'layout/login.tpl', title:  'Login form',
content: contents {
    div (class:'section') {
        main (style:'flex: 1 0 auto;') {
            center {
                div (class:'section') {
                    h5 {
                        span ('Sign In')
                    }
                }
                div (class:'container') {
                    div (class:'z-depth-1 grey lighten-4 row', style:'display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;') {
                        form (class:'col s12', method:'post') {
                            div (class:'row') {
                                div (class: 'col s12') {}
                            }
                            div (class:'row') {
                                div (class:'input-field col s12') {
                                    i (class:'material-icons prefix') {
                                        span('account_circle')
                                    }
                                    input (class:'validate', type:'email', name:'email', id:'email', required:'')
                                    label (for:'email') {
                                        span ('Enter your email')
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
                                        span ('Enter your password')
                                    }
                                }
                                label (style:'float: right;') {
                                    a (class: 'grey-text', href:'#!') {
                                        b ('Forgot Password?')
                                    }
                                }
                            }
                            br
                            center {
                                div (class:'row') {
                                    button (type:'submit', name:'btn_login', class:'col s11 btn btn-large waves-effect waves-light teal lighten-2', style:'margin-left: 25px') {
                                        span ('Login')
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