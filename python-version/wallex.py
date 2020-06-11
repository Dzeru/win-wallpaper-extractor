from tkinter.filedialog import *
import datetime
import os
import shutil


username = ""
where_to_save_dir = ""
default_where_to_save_dir_lbl = "C:\\Users\\USERNAME\\Pictures\\defaultWallpapersDir_NUMBERS"
init_status = "STATUS\n"
status = init_status

lbl_background = "white"
lbl_font = ("Helvetica", 14)


def choose_directory():
    global where_to_save_dir, where_to_save_lbl
    where_to_save_dir = askdirectory() + "/"
    where_to_save_lbl.config(text=where_to_save_dir)


def extract_wallpapers():
    global where_to_save_dir, status, status_lbl, username, username_input
    status = init_status
    username = username_input.get()
    if username == "":
        status += "ERROR! Username is empty!\n"
        status_lbl.config(text=status)
    else:
        default_wallpapers_dir = "C:\\Users\\" + username + "\\AppData\\Local\\Packages\\Microsoft.Windows" \
                                                            ".ContentDeliveryManager_cw5n1h2txyewy\\LocalState" \
                                                            "\\Assets\\"
        if where_to_save_dir == "":
            where_to_save_dir = "C:\\Users\\" + username + "\\Pictures\\defaultWallpapersDir_" + \
                                str(datetime.datetime.today().timestamp()) + "\\"
            os.mkdir(where_to_save_dir)
        try:
            status += "Files will be copied to path " + where_to_save_dir + "\n"
            wallpapers_lst = os.listdir(default_wallpapers_dir)
            for wallpaper in wallpapers_lst:
                to_save_file = default_wallpapers_dir + wallpaper
                saved_file = where_to_save_dir + wallpaper
                if os.path.getsize(to_save_file) > 200:
                    shutil.copyfile(to_save_file, saved_file)
                    try:
                        os.rename(saved_file, saved_file + ".jpg")
                        status += "Copy: " + wallpaper + ".jpg\n"
                        status_lbl.config(text=status)
                    except FileExistsError:
                        status += wallpaper + ".jpg was copied previous time\n"
            status += "DONE."
            status_lbl.config(text=status)
        except Exception:
            status += "ERROR! Something went wrong. Try to choose another folder!\n"
            status_lbl.config(text=status)


root = Tk()
root.title("WallEx")
root.geometry("800x500")
root.config(bg=lbl_background)
username_lbl = Label(text="Username", bg=lbl_background, font=lbl_font)
where_to_save_lbl = Label(text=default_where_to_save_dir_lbl, bg=lbl_background, font=lbl_font)
username_input = Entry(bg=lbl_background, font=lbl_font)
where_to_save_btn = Button(text="Where to save", command=choose_directory, font=lbl_font)
run_btn = Button(text="Run", command=extract_wallpapers, bg="pale green", font=lbl_font)
status_lbl = Label(text=status)
username_lbl.grid(row=0, column=0, padx=10, pady=10)
username_input.grid(row=0, column=1, padx=10, pady=10, sticky="w")
where_to_save_btn.grid(row=1, column=0, padx=10, pady=10)
where_to_save_lbl.grid(row=1, column=1, padx=10, pady=10, sticky="w")
run_btn.grid(row=2, column=0, columnspan=2, padx=10, pady=10, ipadx=50)
status_lbl.grid(row=3, column=0, columnspan=2, padx=10, pady=10, ipadx=50)

root.mainloop()
