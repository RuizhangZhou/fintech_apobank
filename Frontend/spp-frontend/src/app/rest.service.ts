import { Injectable } from '@angular/core';

import { catchError } from 'rxjs/internal/operators';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {getLocaleExtraDayPeriodRules} from "@angular/common";

export interface CustomerAdvertisementId {
  customerId: number,
  advertisementCampaignId: number
}

export interface CustomerAdvertisement {
  id: CustomerAdvertisementId,
  lastDisplayDate: string,
  numberOfTimesDisplayed: number,
  status: string
}

export interface Address {
  street: string,
  houseNumber: string,
  zipCode: number,
  city: string
}

export interface CreditCard {
  id: number,
  creditCardNumber: number,
  creditLine: number,
  nextDebitingDay: string
}

export interface LoanRate{
  id: number,
  dueDate: string,
  payed: boolean,
  dateOfPayment: string,
  sum: 250
}

export interface Loan {
  type: string,
  id: number,
  loanRates: LoanRate[],
  amount: number,
  interest: number,
  balance: number,
  status: string
}

export interface Account {
  type: string,
  id: number,
  accountNumber: number,
  balance: number,
  overdraftInterest: number,
  overdraftLimit: number,
  creditCards: CreditCard[]
}

export interface Investment{
  id: number,
  sum: number,
  interest: number
}

export interface Customer {
  id: 1,
  customerNumber: number,
  firstName: string,
  lastName: string,
  monthlyIncome: number,
  birthday: string,
  customerAdvertisements: CustomerAdvertisement[],
  numberOfChildren: 1,
  address: Address,
  job: string,
  relationshipStatus: string,
  education: string,
  accounts: Account[],
  loans: Loan[],
  investments: Investment[]
}

export interface AdvertisementInfo {
  shouldShow: boolean,
  advertisementCampaignId: number,
  advertisementCampaignStartDate: string,
  advertisementCampaignEndDate: string,
  productId: number,
  productDescription: string,
  productName: string,
  productType: string
}

export interface UpdatePersonalInfo {
  education: string,
  job: string,
  monthlyIncome: number,
  numberOfChildren: number,
  relationshipStatus: string
}

export interface RegisterCustomer {
  birthday: string,
  city: string,
  firstName: string,
  houseNumber: string,
  lastName: string,
  street: string,
  zipCode: number
}

const blUrl = 'http://localhost:8081/business-logic/v1/';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  test_customer_number: number = 100000;

  constructor(private http: HttpClient) { }

  private extractData(res: Response): any {
    const body = res;
    return body || { };
  }

  /*
      ACCOUNT CONTROLLER
   */

  getAccountsByCustomer(customer_number: number): Observable<any> {
    return this.http.get<Account[]>(blUrl + 'accounts?customer_number=' + customer_number).pipe(
      catchError(this.handleError)
    );
  }

  //TODO: createAccount Method (does not work in business-logic)

  transferMoney(account_number: string, send_to: string, amount: number): Observable<any> {
    return this.http.put<string>(blUrl + 'accounts?account_number='+account_number+'&send_to='+send_to, amount).pipe(
      catchError(this.handleError)
    );
  }

  getAccount(account_number: number): Observable<any> {
    return this.http.get<Account>(blUrl + 'accounts/' + account_number).pipe(
      catchError(this.handleError)
    );
  }

  /*
      ADVERTISEMENT CONTROLLER
   */

  getAdvertisementInfo(customer_number: number): Observable<any> {
    return this.http.get<AdvertisementInfo[]>(blUrl + 'advertisement?customer_number=' + customer_number).pipe(
      catchError(this.handleError)
    );
  }

  /*
      CUSTOMER CONTROLLER
   */

  getCustomer(customer_number: number): Observable<any> {
    return this.http.get<Customer>(blUrl + 'customers/' + customer_number).pipe(
      catchError(this.handleError)
    );
  }

  updateAddress(customer_number: number, address: Address): Observable<any> {
    return this.http.put<Customer>(blUrl + 'customers/'+customer_number+'/address', address).pipe(
      catchError(this.handleError)
    );
  }

  updatePersonalInfo(customer_number: number, updatePersonalInfo: UpdatePersonalInfo): Observable<any> {
    return this.http.put<Customer>(blUrl + 'customers/'+customer_number+'/personal-info', updatePersonalInfo).pipe(
      catchError(this.handleError)
    );
  }

  login(customer_number: string, password: string): Observable<any> {
    return this.http.get<boolean>(blUrl+'customers/login?customer_number='+customer_number+'&password='+password).pipe(
      catchError(this.handleError)
    );
  }

  registerCustomer(registerCustomer: RegisterCustomer): Observable<any> {
    return this.http.post<Customer>(blUrl + 'customers/register', registerCustomer).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): any {
    if (error.error instanceof ErrorEvent) {
      console.error('Error: ', error.error.message);
    } else {
      console.error(
        `Http Status: ${error.status}, ` +
        `Http Body: ${error.error}`);
    }
    return throwError(
      'Error while handling http request.');
  }
}
