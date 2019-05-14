import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpClientService} from '../services/http-client.service';
import {Users} from '../models/Users';

@Component({
  selector: 'app-order-manager',
  templateUrl: './order-manager.component.html',
  styleUrls: ['./order-manager.component.css']
})
export class OrderManagerComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
  users: Users[] = [];

  constructor(private formBuilder: FormBuilder, private httpClient: HttpClientService) {
  }

  ngOnInit() {
    this.getUsers();
    this.registerForm = this.formBuilder.group({
      itemId: ['', Validators.required ],
      orderDesc: ['', Validators.required],
      userId: ['', Validators.required],
    });
  }

  // convenience getter for easy access to form fields
  get field() {
    return this.registerForm.controls;
  }

  getUsers() {
    this.httpClient.getUsers().subscribe((res: Users[]) => {
      console.log(res);
      this.users = res;
      if (res.length > 1) {
        this.registerForm.get('userId').setValue(res[0].id);
      }
    });
  }

  onSubmit() {
    this.submitted = true;
    console.log(this.registerForm.value);

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.httpClient.postUsers(this.registerForm.value)
      .subscribe((res: Users[]) => {
        console.log(res);
      }, error => console.log(error));
  }

  updateFormDetail(event) {
    this.registerForm.get('userId').setValue(event.target.value);
  }

}
