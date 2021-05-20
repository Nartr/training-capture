import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { PrincipalDto } from '../../dtos/PrincipalDto';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  private principal: PrincipalDto | undefined;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getPrincipal().subscribe(dto => {
      this.principal = dto;
    }, () => {
      this.principal = undefined;
    })
  }

  public getUsername(): string {
    return this.principal ? this.principal.username : 'Nicht eingeloggt';
  }
}
