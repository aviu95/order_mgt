import { Component, OnInit } from '@angular/core';
import {HttpClientService} from '../services/http-client.service';
import {Order, PagenatedDetails} from '../models/Orders';


const ELEMENT_DATA: PagenatedDetails<Order> = {items: [
  ], pageIndex: 0, size: 10, totalPages: 1};

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  displayedColumns: string[] = ['id', 'productDesc', 'firstName', 'lastName'];
  orderDetails: PagenatedDetails<Order> = ELEMENT_DATA;
  pageSize = 10;
  pageIndex = 0;
  totalLength = 10;

  constructor(private httpService: HttpClientService) { }

  ngOnInit() {
    this.getOrders(this.pageIndex, this.pageSize);
  }

  pageChanged(event) {
    console.log(event);
    this.getOrders(event.pageIndex, event.pageSize);
  }

  getOrders(index, size) {
    this.httpService.getOrders(index, size).subscribe((res: PagenatedDetails<Order>) => {
     console.log(res);
     this.orderDetails = res;
     this.pageIndex = res.pageIndex;
     this.pageSize = res.size;
     this.totalLength = res.size * res.totalPages;
    }, error => {
      console.log(error);
    });
  }

}
