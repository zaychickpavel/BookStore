


import {ChangeDetectorRef} from "@angular/core";
import {BookStoreEntityInterface} from "../models/bookstore-entity-interface";

export class ImageUtils {
	constructor(private changeDetectorRef: ChangeDetectorRef, private bookstore: BookStoreEntityInterface) {
	}


	public showImage(files: FileList) {
		this.readFiles(files);
	}

	public readImage(files: FileList) {
		this.readFiles(files);
	}

	private readFile(file : any, reader : FileReader, callback : any){
		reader.onload = () => {
			callback(reader.result);
		}

		reader.readAsDataURL(file);
	}

	private readFiles(files : FileList, index=0){
		// Create the file reader
		let reader = new FileReader();

		// If there is a file
		if(index in files){
			// Start reading this file
			this.readFile(files[index], reader, (result : any) =>{
				// Create an img element and add the image file data to it
				var img: HTMLImageElement = document.createElement("img");
				img.src = result;
				// Send this img to the resize function (and wait for callback)
				this.resize(img, 600, 600, (resized_jpeg : any, before : any, after : any)=>{
					// For debugging (size in bytes before and after)

					// Add the resized jpeg img source to a list for preview
					// This is also the file you want to upload. (either as a
					// base64 string or img.src = resized_jpeg if you prefer a file).
					this.bookstore.image = resized_jpeg;

					// Read the next file;
					this.readFiles(files, index+1);
				});
			});
		}else{
			// When all files are done This forces a change detection
			this.changeDetectorRef.detectChanges();
		}
	}


	private resize(img: HTMLImageElement, MAX_WIDTH:number, MAX_HEIGHT:number, callback: any){
		// This will wait until the img is loaded before calling this function
		return img.onload = () => {
			// Get the images current width and height
			var width = img.width;
			var height = img.height;

			// Set the WxH to fit the Max values (but maintain proportions)
			if (width > height) {
				if (width > MAX_WIDTH) {
					height *= MAX_WIDTH / width;
					width = MAX_WIDTH;
				}
			} else {
				if (height > MAX_HEIGHT) {
					width *= MAX_HEIGHT / height;
					height = MAX_HEIGHT;
				}
			}
			// create a canvas object
			var canvas = document.createElement("canvas");

			// Set the canvas to the new calculated dimensions
			canvas.width = width;
			canvas.height = height;
			var ctx = canvas.getContext("2d");

			ctx.drawImage(img, 0, 0,  width, height);

			// Get this encoded as a jpeg
			// IMPORTANT: 'jpeg' NOT 'jpg'
			var dataUrl = canvas.toDataURL('image/jpeg');

			// callback with the results
			callback(dataUrl, img.src.length, dataUrl.length);
		};
	}
}