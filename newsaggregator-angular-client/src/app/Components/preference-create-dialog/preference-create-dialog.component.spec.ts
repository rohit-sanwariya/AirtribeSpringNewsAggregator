import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreferenceCreateDialogComponent } from './preference-create-dialog.component';

describe('PreferenceCreateDialogComponent', () => {
  let component: PreferenceCreateDialogComponent;
  let fixture: ComponentFixture<PreferenceCreateDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PreferenceCreateDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PreferenceCreateDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
