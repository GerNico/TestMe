var app = angular.module('TestingGalaxy', []);

app.controller('navBarController', function () {
    this.navBarHome={reference: "/root/home", label: "Testing Galaxy", theClass: "navbar-brand"};

    this.menuList = [
        {reference: "/root/home", label: "Find tests", theClass: ""},
        {reference: "/root/login", label: "Sign in", theClass: "active"},
        {reference: "/root/signup", label: "Sign up", theClass: ""}
    ];

    this.dropdownMenuList = {
        htmlCaptionIcon: "glyphicon glyphicon-list-alt",
        htmlCaptionLabel: "My courses",
        listItems: [
            {reference: "/test/1", label: "first test", theClass: ""},
            {reference: "/test/2", label: "second test", theClass: "active"}
        ]
    }
});

app.controller('loginPageController', function () {
    this.magingTop="margin-top:"+screen.height/8+"px";
    this.user={
        login:"",
        password:"",
        toRemember:false
    };
    this.passwordRecovering={reference: "/root/forgot", label: "Forgot password?"};

        this.submitLoginForm = function () {
            console.log("hoho");
            alert(user);
        }

}
);
