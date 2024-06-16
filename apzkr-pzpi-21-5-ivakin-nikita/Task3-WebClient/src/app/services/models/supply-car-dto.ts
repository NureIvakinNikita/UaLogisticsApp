/* tslint:disable */
/* eslint-disable */
import { LogisticCompanyDto } from '../models/logistic-company-dto';
import { SupplyRequestDto } from '../models/supply-request-dto';
export interface SupplyCarDto {
  id?: number;
  logisticCompany?: LogisticCompanyDto;
  logisticCompanyId?: number;
  supplyRequest?: SupplyRequestDto;
  supplyRequestId?: number;
  tier?: number;
}
