/*active button class onclick*/
$('nav a').click(function(e) {
    e.preventDefault();
    $('nav a').removeClass('active');
    $(this).addClass('active');
    if(this.id === !'payment'){
      $('.payment').addClass('noshow');
    }
    else if(this.id === 'payment') {
      $('.payment').removeClass('noshow');
      $('.rightbox').children().not('.payment').addClass('noshow');
    }
    else if (this.id === 'profile') {
      $('.profile').removeClass('noshow');
       $('.rightbox').children().not('.profile').addClass('noshow');
    }
    else if(this.id === 'subscription') {
      $('.subscription').removeClass('noshow');
      $('.rightbox').children().not('.subscription').addClass('noshow');
    }
      else if(this.id === 'privacy') {
      $('.privacy').removeClass('noshow');
      $('.rightbox').children().not('.privacy').addClass('noshow');
    }
    else if(this.id === 'settings') {
      $('.settings').removeClass('noshow');
      $('.rightbox').children().not('.settings').addClass('noshow');
    }
  });


  
function _defineProperty(obj, key, value) {
  if (key in obj) {
      Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true });
  } else {
      obj[key] = value;
  }
  return obj;
}
var config = {
  apiKey: "AIzaSyAH_ooTUeL-zbmqwiPWxeoymFRrcAyomYs",
  authDomain: "datingapp-8390d.firebaseapp.com",
  databaseURL: "https://datingapp-8390d-default-rtdb.firebaseio.com",
  projectId: "datingapp-8390d",
  storageBucket: "datingapp-8390d.appspot.com",
  messagingSenderId: "182475800709",
  appId: "1:182475800709:web:48ac3a2bba138f08f769ec",
  measurementId: "G-8CN7BQQRNC" };

firebase.initializeApp(config);


class UploadForm extends React.Component {

  constructor(props) {
    super(props);_defineProperty(this, "handleUpload",

    e => {
      const file = e.target.files[0];
      if (file.type === 'image/png' || file.type === 'image/jpeg') {
        this.setState({ picture: file });
      } else {
        this.setState({ error: "Invalid file format" });
      }
    });_defineProperty(this, "handleClick",

    () => {
      if (this.state.name !== '' && this.state.picture !== null) {
        this.props.onClick(this.state);
        this.setState({ error: null, name: '', picture: null });
      } else {
        this.setState({ error: "Hãy tải ảnh lên trước" });
      }
    });this.state = { picture: null, name: '', error: null };}

  handleChange(e) {
    const value = e.target.value;
    this.setState({ name: value });
  }

  render() {
    return /*#__PURE__*/(
      React.createElement("div", { className: "upload-form" }, /*#__PURE__*/
      React.createElement("div", { className: "upload-form__wrap" }, /*#__PURE__*/

      React.createElement("div", { className: "upload-form__field" }, /*#__PURE__*/
      React.createElement("h2", null, "Picture:"), /*#__PURE__*/
      React.createElement("input", { type: "file", onChange: e => this.handleUpload(e), accept: "image/gif, image/jpeg, image/png" })), /*#__PURE__*/

      
      React.createElement("div", { className: "upload-form__field" }, /*#__PURE__*/
      React.createElement("h2", null, "Name:"), /*#__PURE__*/
      React.createElement("input", { type: "text", value: this.state.name, onChange: e => this.handleChange(e) })), /*#__PURE__*/


      React.createElement("div", { className: "upload-form__submit" }, /*#__PURE__*/
      React.createElement("button", { className: "upload-form__submit__action", onClick: () => this.handleClick() }, "Upload Picture")),


      this.state.error && /*#__PURE__*/
      React.createElement("p", { className: "error" }, this.state.error))));




  }}


class App extends React.Component {

  constructor(props) {
    super(props);_defineProperty(this, "handleUpload",


    data => {

      const file = data.picture;
      const storageRef = firebase.storage().ref(`images/${file.name}`);
      const task = storageRef.put(data.picture);

      // Listener que se ocupa del estado de la carga del fichero
      task.on('state_changed', snapshot => {
        // console.log('loading...');

      }, error => {
        console.error(error.message);

      }, () => {

        const record = {
          image: task.snapshot.downloadURL,
          displayName: data.name };


        const dbRef = firebase.database().ref('/pictures');
        const newPicture = dbRef.push();
        newPicture.set(record);

      });
    });this.state = { pictures: [] };}componentWillMount() {firebase.database().ref('pictures').on('child_added', snapshot => {
      const picture = snapshot.val();
      // if (picture.displayName == "k") 
      {
        console.log(picture.displayName)
        this.setState({ pictures: this.state.pictures.concat(picture) });
      }
    });}

  render() {

    return /*#__PURE__*/(
      React.createElement("div", { className: "app-wrap" }, /*#__PURE__*/
      React.createElement(UploadForm, { onClick: this.handleUpload }), /*#__PURE__*/
      React.createElement("div", { className: "grid-wrap" }, /*#__PURE__*/
      React.createElement("ul", { className: "picture-list" },

      this.state.pictures.map((picture, index) => /*#__PURE__*/
      React.createElement("li", { className: "picture-list__item", key: index }, /*#__PURE__*/
      React.createElement("div", { className: "picture-list__item__wrap" }, /*#__PURE__*/
      React.createElement("img", { className: "picture-list__item__image", src: picture.image, alt: picture.displayName }) /*#__PURE__*/
))).


      reverse()))));






  }}



ReactDOM.render( /*#__PURE__*/React.createElement(App, null), document.getElementById('root'));