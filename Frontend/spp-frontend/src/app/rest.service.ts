import { Injectable } from '@angular/core';

import { catchError } from 'rxjs/internal/operators';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import {Observable, of, throwError} from 'rxjs';
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

  test_customer_number: string = '0';

  test_customer: Customer = <Customer> {
    id: 1,
    customerNumber: 100000,
    firstName: "Martin",
    lastName: "Vichev",
    monthlyIncome: 50,
    birthday: "1999-03-30",
    customerAdvertisements: [
      {
        id: {
          customerId: 1,
          advertisementCampaignId: 3
        },
        lastDisplayDate: "2020-12-20",
        numberOfTimesDisplayed: 5,
        status: "SUCCESS"
      },
      {
        id: {
          customerId: 1,
          advertisementCampaignId: 2
        },
        lastDisplayDate: "2020-12-19",
        numberOfTimesDisplayed: 4,
        status: "SUCCESS"
      },
      {
        id: {
          customerId: 1,
          advertisementCampaignId: 1
        },
        lastDisplayDate: "2020-12-19",
        numberOfTimesDisplayed: 14,
        status: "SUCCESS"
      }
    ],
    numberOfChildren: 1,
    address: {
      street: "Fakestreet",
      houseNumber: "20",
      zipCode: 52070,
      city: "Yaytown"
    },
    job: "ENTREPRENEUR",
    relationshipStatus: "REGISTERED_PARTNERSHIP",
    education: "UNKNOWN",
    accounts: [
      {
        type: "currentAccount",
        id: 1,
        accountNumber: 10000000,
        balance: 12540.54,
        overdraftInterest: 0.16,
        overdraftLimit: 2000,
        creditCards: [
          {
            id: 1,
            creditCardNumber: 1111,
            creditLine: 0.56,
            nextDebitingDay: "2021-01-29T03:14:07.999999"
          }
        ]
      },
      {
        type: "callMoneyAccount",
        id: 7,
        accountNumber: 10000006,
        balance: 1000,
        overdraftInterest: 1
      }
    ],
    loans: [
      {
        type: "carLoan",
        id: 1,
        loanRates: [
          {
            id: 1,
            dueDate: "2020-12-29",
            payed: true,
            dateOfPayment: "2020-12-29",
            sum: 250
          }
        ],
        amount: 10000,
        interest: 1.5,
        balance: 2000,
        status: "DEFAULT"
      }
    ],
    investments: [
      {
        id: 1,
        sum: 10000,
        interest: 5
      }
    ]
  };

  test_customer_info: AdvertisementInfo[] = [
      {
        "shouldShow": true,
        "advertisementCampaignId": 3,
        "advertisementCampaignStartDate": "2020-12-01",
        "advertisementCampaignEndDate": "2021-12-01",
        "productId": 3,
        "productDescription": "Product Type",
        "productName": "Product Description.",
        "productType": "Product Name"
      },
    {
      "shouldShow": true,
      "advertisementCampaignId": 2,
      "advertisementCampaignStartDate": "2020-12-01",
      "advertisementCampaignEndDate": "2022-07-01",
      "productId": 2,
      "productDescription": "Aktien",
      "productName": "Investieren Sie in einem erfolgreichen Startup aus Aachen!",
      "productType": "Aktien"
    }
];

  test_customer_accounts: Account[] = [
    {
      type: "currentAccount",
      id: 1,
      accountNumber: 10000000,
      balance: 12540.54,
      overdraftInterest: 0.16,
      overdraftLimit: 2000,
      creditCards: [
        {
          id: 1,
          creditCardNumber: 1111,
          creditLine: 0.56,
          nextDebitingDay: "2021-01-29T03:14:07.999999"
        }
      ]
    }
  ];

  constructor(private http: HttpClient) { }

  private extractData(res: Response): any {
    const body = res;
    return body || { };
  }

  /*
      ACCOUNT CONTROLLER
   */

  getAccountsByCustomer(customer_number: string): Observable<any> {
    if(customer_number === '0'){
      return of(this.test_customer_accounts);
    }
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

  getAccount(account_number: string): Observable<any> {
    return this.http.get<Account>(blUrl + 'accounts/' + account_number).pipe(
      catchError(this.handleError)
    );
  }

  /*
      ADVERTISEMENT CONTROLLER
   */

  getAdvertisementInfo(customer_number: string): Observable<any> {
    if(customer_number === '0'){
      return of(this.test_customer_info);
    }
    return this.http.get<AdvertisementInfo[]>(blUrl + 'advertisement?customer_number=' + customer_number).pipe(
      catchError(this.handleError)
    );
  }

  /*
      CUSTOMER CONTROLLER
   */

  getCustomer(customer_number: string): Observable<any> {
    if(customer_number === '0'){
      return of(this.test_customer);
    }
    return this.http.get<Customer>(blUrl + 'customers/' + customer_number).pipe(
      catchError(this.handleError)
    );
  }

  updateAddress(customer_number: string, address: Address): Observable<any> {
    return this.http.put<Customer>(blUrl + 'customers/'+customer_number+'/address', address).pipe(
      catchError(this.handleError)
    );
  }

  updatePersonalInfo(customer_number: string, updatePersonalInfo: UpdatePersonalInfo): Observable<any> {
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
