
# Update the package repository
sudo yum update -y

# Install Amazon Corretto 17 (headless variant)
sudo yum install -y java-17-amazon-corretto-headless

# Verify the installation
java_version=$(java -version 2>&1 >/dev/null | grep 'openjdk version' | awk '{print $3}')
echo "Java Installed Version: $java_version"

# Set JAVA_HOME
# java_path=$(update-alternatives --config java | grep java-17-amazon-corretto | head -n 1)
# java_home=$(dirname $(dirname $java_path))
# echo "export JAVA_HOME=$java_home" >> ~/.bashrc
# echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> ~/.bashrc
# source ~/.bashrc

echo "Amazon Corretto 17 installation and setup completed."