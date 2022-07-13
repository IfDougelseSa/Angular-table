import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/entities/user/User';
import { UserService } from 'src/app/entities/user/user.service';


@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  users: User[] = [];
  nameUser = '';
  age = '';
  id = 0;
  constructor(private userService: UserService) { 
   
  }

  ngOnInit(): void {
    
  }

  userAlert() {
    alert('Created user')
  }

  addUser() {
    const newUser: User = {
      id: this.id,
      name: this.nameUser,
      age: parseInt(this.age)
    }
    this.userService.addUser(newUser).subscribe(user => this.users.push(user));
    alert('Create user')
  }

}
