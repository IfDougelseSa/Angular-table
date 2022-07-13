import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/entities/user/user.service';
import { User } from 'src/app/entities/user/User';


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnInit {

  users: User[] = [];

  nameUser = '';
  age = '';
  id = 0;
  

  constructor(private userService: UserService) {
    this.getAllUsers();
 
  }

  

  ngOnInit(): void {
   
  }

  

  getAllUsers(): void {
    this.userService.getAllUser().subscribe((user) => (this.users = user))
  }

  addUser() {
    const newUser: User = {
      id: this.id,
      name: this.nameUser,
      age: parseInt(this.age)
    }
    this.userService.addUser(newUser).subscribe(user => this.users.push(user));
  }

  deleteUser(user: User) {
    this.users = this.users.filter((u) => user.name != u.name);
    this.userService.deleteUser(user.id).subscribe();
  }


}
