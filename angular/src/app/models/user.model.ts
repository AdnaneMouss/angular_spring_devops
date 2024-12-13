export interface User {
  id: number;
  name: string;
  email: string;
  type: UserType;
}

export enum UserType {
  ADMIN = 'ADMIN',
  EMPLOYEE = 'EMPLOYEE',
  SUPPLIER = 'SUPPLIER'
}
