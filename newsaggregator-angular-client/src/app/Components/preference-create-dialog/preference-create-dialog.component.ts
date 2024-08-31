import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { TKeyValue } from '../../Models/user-auth-model';
import { PreferencesDialogComponent } from '../preferences-dialog/preferences-dialog.component';

@Component({
  selector: 'app-preference-create-dialog',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatButtonModule,
  ],
  templateUrl: './preference-create-dialog.component.html',
  styleUrl: './preference-create-dialog.component.scss'
})
export class PreferenceCreateDialogComponent {

  preferences: FormControl = new FormControl({value: null,disabled: false},{validators:[Validators.required]});
   

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<PreferencesDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data:{selectedPreferences:TKeyValue[]
      ,allPreferences:TKeyValue[]}
  ) {
     
  }
  save(): void { 
    this.dialogRef.close(this.preferences.value);
  }

  close(): void {
    this.dialogRef.close();
  }
}
