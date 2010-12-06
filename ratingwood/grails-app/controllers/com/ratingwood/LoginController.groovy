package com.ratingwood

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH


class LoginController {

    def facebookConnect = {
        String facebookAuthorizeUrl = "https://graph.facebook.com/oauth/authorize?client_id=${CH.config.facebookConnect.AppId}&redirect_uri=${CH.config.grails.serverURL}/login/getFacebookToken/&scope=${CH.config.facebookConnect.faceBookPermissions}"
        println "facebookAuthorizeUrl: ${facebookAuthorizeUrl}"
        redirect(url: facebookAuthorizeUrl)
    }

    def getFacebookToken = {
        String authCode = params.code
        if (authCode) {
            String facebookTokenUrl = "https://graph.facebook.com/oauth/access_token?client_id=${CH.config.facebookConnect.AppId}&client_secret=${CH.config.facebookConnect.SecretKey}&code=${authCode}&redirect_uri=${CH.config.grails.serverURL}/login/getFacebookToken/&scope=${CH.config.facebookConnect.faceBookPermissions}"
            URL url = new URL(facebookTokenUrl)
            String response = url.text
            if (response.contains('access_token=')) {
                String[] resp = response.split('access_token=')
                String accessToken = resp[1]
                println "Facebook Access Token: ${accessToken}"
                session['fb_token'] = accessToken
            }
        }
        redirect(controller: "home", action: "index")
    }

}
