/* tslint:disable */
/* eslint-disable */
export interface RegisterRequest {
  age?: number;
  email: string;
  firstName: string;
  lastName: string;
  passportNumber: string;
  password: string;
  post?: 'BRIGADE_COMMANDER' | 'REGIMENT_COMMANDER' | 'BATTALION_COMMANDER' | 'COMPANY_COMMANDER' | 'PLAT_COMMANDER' | 'LOGISTIC_COMMANDER';
  rank: 'MAJOR_GENERAL' | 'COLONEL' | 'MAJOR' | 'LIEUTENANT_COLONEL' | 'SENIOR_LIEUTENANT' | 'JUNIOR_LIEUTENANT' | 'SCANNING_DEVICE';
  role: 'BRIGADE_COMMANDER' | 'BATTALION_COMMANDER' | 'COMPANY_COMMANDER' | 'PLAT_COMMANDER' | 'LOGISTIC_COMMANDER' | 'SCANNING_DEVICE' | 'ADMIN';
  secondName?: string;
}
