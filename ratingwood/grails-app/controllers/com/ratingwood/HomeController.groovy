package com.ratingwood

class HomeController {

    def index = {
        if(session['fb_token']){
            render ("Hey man! You are connected with Facebook already")
        }else{
            redirect(controller:'login', action:'facebookConnect')
        }
    }
}
