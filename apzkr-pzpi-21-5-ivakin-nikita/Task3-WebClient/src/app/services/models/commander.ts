/* tslint:disable */
/* eslint-disable */
export interface Commander {
    age?: number;
    battalionGroupId?: number;
    email?: string;
    firstName?: string;
    id?: number;
    lastName?: string;
    rank?: 'MAJOR_GENERAL' | 'COLONEL' | 'MAJOR' | 'LIEUTENANT_COLONEL' | 'SENIOR_LIEUTENANT' | 'JUNIOR_LIEUTENANT' | 'SCANNING_DEVICE';
    role?: 'BRIGADE_COMMANDER' | 'BATTALION_COMMANDER' | 'COMPANY_COMMANDER' | 'PLAT_COMMANDER' | 'LOGISTIC_COMMANDER' | 'SCANNING_DEVICE' | 'ADMIN';
    secondName?: string;
    passportNumber?: string | number;
  }
  