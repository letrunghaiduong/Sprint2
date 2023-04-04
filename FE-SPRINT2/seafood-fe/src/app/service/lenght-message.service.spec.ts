import { TestBed } from '@angular/core/testing';

import { LenghtMessageService } from './lenght-message.service';

describe('LenghtMessageService', () => {
  let service: LenghtMessageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LenghtMessageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
