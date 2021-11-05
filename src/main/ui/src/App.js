import './App.css';
import { useEffect, useState } from 'react';

function App() {
  const [status, setStatus] = useState('PENDING');
  const [buttonDisabled, setButtonDisabled] = useState(true);
  const [buttonText, setButtonText] = useState('Wait...');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    refresh();
  }, []);

  useEffect(() => {
    setButtonDisabled(isButtonDisabled(status));
    setButtonText(getButtonText(status));
  }, [status]);

  const refresh = () => {
    fetch('/api/status').then(response => response.json()).then(json => {
      setStatus(json.status);
      setLoading(false);
    });
  };

  const getButtonText = (status) => {
    switch (status) {
      case 'RUNNING':
        return 'Stop Server';
      case 'STOPPED':
        return 'Start Server';
      case 'PENDING':
      case 'STOPPING':
        return 'Wait...';
      default:
        return 'I\'m broken. Tell the other vikings about it.';
    }
  }

  const isButtonDisabled = (status) => status !== 'RUNNING' && status !== 'STOPPED';

  const handleClick = () => {
    let url;
    if (status === 'RUNNING') {
      url = '/api/ec2/stop';
    } else if (status === 'STOPPED') {
      url = '/api/ec2/start';
    } else {
      setStatus();
      return;
    }
    setLoading(true);
    fetch(url, {
      method: 'POST'
    })
    .then(response => response.json())
    .then(json => {
      setStatus(json.status);
      refresh();
    });
  };

  return (
    <div className="App">
      <header className="App-header">
        <p disabled={loading}>
          Server Status: {status}
        </p>
        <button className="App-button" disabled={buttonDisabled || loading} onClick={handleClick}>
          {buttonText}
        </button>
        <br/>
        <button disabled={loading} className="App-button" onClick={refresh}>Refresh</button>
      </header>
    </div>
  );
}

export default App;
