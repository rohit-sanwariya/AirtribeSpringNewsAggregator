import { NgFor } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, ReactiveFormsModule, FormControl } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import {  MatSelectModule } from '@angular/material/select';
import { TKeyValue } from '../../Models/user-auth-model';

@Component({
  selector: 'app-preferences-dialog',
  standalone: true,
  imports: [MatButtonModule,ReactiveFormsModule,MatFormFieldModule,MatSelectModule,MatIconModule,NgFor],
  templateUrl: './preferences-dialog.component.html',
  styleUrl: './preferences-dialog.component.scss'
})
export class PreferencesDialogComponent {
  preferences: FormControl = new FormControl();
  availablePreferences: {id:number,name:string}[] = [];
  selectedPreferences: {id:number,name:string}[] = [];

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<PreferencesDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data:{selectedPreferences:TKeyValue[]
      ,allPreferences:TKeyValue[]}
  ) {
    this.dialogRef.afterOpened().subscribe(data => {
      this.preferences.setValue(this.data.selectedPreferences.map(preference => preference.id));
    } )
  }

 

 

 

  save(): void {
 
    this.dialogRef.close(this.preferences.value);
  }

  close(): void {
    this.dialogRef.close();
  }
}
