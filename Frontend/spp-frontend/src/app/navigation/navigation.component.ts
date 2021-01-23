import { Component, OnInit } from '@angular/core';
import {NAVIGATION} from '../Models/Navigation';
import {AppRoutingModule} from '../app-routing.module';
import {Router} from '@angular/router';
import {NavItem} from '../Models/NavItem';
import { Location } from '@angular/common';
import {CustomerInfoComponent} from '../customer-info/customer-info.component';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import {CustomerInfoPersonalComponent} from '../customer-info-personal/customer-info-personal.component';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  constructor(private router: Router, private location: Location, private dialog: MatDialog/*public dialogRef: MatDialogRef<NavigationComponent>*/) {}
  navigation = NAVIGATION;
  public currentSelected = '';

  ngOnInit(): void {
    console.log(this.location.path());
    switch (this.location.path()) {
      case '':
        this.currentSelected = 'Übersicht';
        break;
      case '/customer-info':
        this.currentSelected = 'Meine Daten';
        break;
      case '/accounts':
        this.currentSelected = 'Konten';
        break;
      case '/credits':
        this.currentSelected = 'Kredite';
        break;
      case '/investments':
        this.currentSelected = 'Geldanlagen';
        break;
    }
  }

  onSelect(page: NavItem): void{
    this.currentSelected = page.name;
    this.router.navigateByUrl(page.path);
  }

  onLogin(): void{/*
    let dialogRef = dialog.open(CustomerInfoComponent, {
      height: '400px',
      width: '600px',
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`); // Pizza!
    });
*/
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '200px';
    dialogConfig.height = '200px';
/*
    dialogConfig.data = {
      id: 1,
      title: 'Angular For Beginners'
    };
    */
    dialogConfig.position = {
      top: '100px',
      left: '100px'
    };

    this.dialog.open(CustomerInfoPersonalComponent, dialogConfig);

    const dialogRef = this.dialog.open(CustomerInfoPersonalComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => console.log('Dialog output:', data)
    );
  }
}
