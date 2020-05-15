#/bin/bash

## installation of veracrypt
sudo add-apt-repository ppa:unit193/encryption
sudo apt update
sudo apt install -y veracrypt

## Generate keyfile
veracrypt --create-keyfile #interactive

## Volume creation
## Use 10M as size
PASSWORD="eKPClTK+IfHEBAASaUfCBWis0Y143h2i7uLM3ly+7PM="
veracrypt -t -c --volume-type=normal ./enc_volume --encryption=aes --hash=sha-512 --filesystem=ext4 -p $PASSWORD --pim=0 -k ./keyfile --random-source=/dev/urandom  --size=10M
## mount encrypted
mkdir -p mnt
veracrypt -t -k ./keyfile -p $PASSWORD --pim=0 --protect-hidden=no ./enc_volume ./mnt/

## read keyfile
cat ./keyfile

## backup and see if it works
cp enc_volume enc_volume.bkp
sudo bash -c ' echo "basurita" > mnt/basura'
sudo bash -c ' ls -alh mnt'
veracrypt -d ./mnt
rm -rfv enc_volume
mv enc_volume.bkp enc_volume
veracrypt -t -k ./keyfile -p $PASSWORD --pim=0 --protect-hidden=no ./enc_volume ./mnt/
sudo bash -c ' ls -alh mnt'
veracrypt -d ./mnt


## Create hidden volume
PASSWORD_HIDDEN="q7AakbEP/kjSGcnpy6JbaNbXFtFD5wII+lrnJAHfnfo="
echo "Seguridad Informática 2020" > ./keyfile_hidden
veracrypt -t -c --volume-type=hidden ./enc_volume --encryption=camellia --hash=sha-256 --filesystem=ext4 -p $PASSWORD_HIDDEN --pim=0 -k ./keyfile_hidden --random-source=/dev/urandom --size=5M

## mount hidden
mkdir -p mnt_hidden
veracrypt -t -k ./keyfile_hidden -p $PASSWORD_HIDDEN --pim=0 --protect-hidden=no ./enc_volume ./mnt_hidden/

## check hidden
sudo bash -c ' ls -alh mnt_hidden'
veracrypt -d ./mnt_hidden

## Generate GPG key
mkdir -p gpg_home
export GNUPGHOME="./gpg_home"
rm -rfv gpg_config
echo "%echo Generating a basic OpenPGP key" >> gpg_config
echo "Key-Type: DSA" >> gpg_config
echo "Key-Length: 1024" >> gpg_config
echo "Subkey-Type: ELG-E" >> gpg_config
echo "Subkey-Length: 1024" >> gpg_config
echo "Name-Real: Erik Regla" >> gpg_config
echo "Name-Comment: uwu 110%" >> gpg_config
echo "Name-Email: eregla@alumnos.utalca.cl" >> gpg_config
echo "Expire-Date: 0" >> gpg_config
echo "Passphrase: password" >> gpg_config
echo "%commit" >> gpg_config
echo "%echo done" >> gpg_config
gpg --batch --generate-key gpg_config
gpg --list-secret-keys

## sign this report and check
cp ../informe.pdf target.pdf
gpg --sign ./target.pdf ## you will be prompted
gpg --verify ./target.pdf.gpg
gpg --output target.decripted.pdf --decrypt target.pdf.gpg
diff target.decripted.pdf target.pdf ## should be empty as there is no changes between the files

