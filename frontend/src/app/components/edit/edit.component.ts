import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/entities/user/User';
import { UserService } from 'src/app/entities/user/user.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  user?: User;

  nameUser = '';
  age = '';
  id = 0;

  constructor(private userService: UserService, private route: ActivatedRoute) {
    this.getUser();
  }

  ngOnInit(): void {
    
  }

  getUser() {
    const id = Number(this.route.snapshot.paramMap.get("id"))
    this.userService.getUser(id).subscribe((user) => (this.user = user));
    return id;
  }

  updateUser(value: any) {
    let body = {
      name: value.nameUser,
      age: value.age
    }

    this.userService.updateUser(body, this.getUser().toString())
      .subscribe(response => {
        console.log(response)
      })
      alert('Updated user');
  }
/*
  updateUser() {
    const updateUser: User = {
      id: this.id,
      name: this.nameUser,
      age: parseInt(this.age)
    }

    this.userService.updateUser(updateUser, this.id).subscribe();
  }*/
}
