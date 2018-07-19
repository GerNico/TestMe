import {Component} from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {
  navBarHome = {reference: '/root/home', label: 'Testing Galaxy', theClass: 'navbar-brand'};

  menuList = [
    {reference: '/root/home', label: 'Find tests', theClass: ''},
    {reference: '/root/login', label: 'Sign in', theClass: 'active'},
    {reference: '/root/signup', label: 'Sign up', theClass: ''}
  ];

  dropdownMenuList = {
    htmlCaptionIcon: 'glyphicon glyphicon-list-alt',
    htmlCaptionLabel: 'My courses',
    listItems: [
      {reference: '/test/1', label: 'first test', theClass: ''},
      {reference: '/test/2', label: 'second test', theClass: 'active'}
    ]
  };
}
