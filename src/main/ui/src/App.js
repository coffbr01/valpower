import React, { useEffect, useState } from 'react';
import { Button, createTheme, CssBaseline, ThemeProvider } from '@mui/material';
import { Box } from '@mui/system';
import StatusIcon from './StatusIcon';

const theme = createTheme();

const App = () => {
  const [status, setStatus] = useState();
  const [buttonDisabled, setButtonDisabled] = useState(true);
  const [buttonText, setButtonText] = useState('Wait...');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    refresh();
  }, []);

  useEffect(() => {
    setButtonDisabled(isButtonDisabled(status));
    setButtonText(getButtonText(status));
  }, [status]);

  const refresh = () => {
    fetch('/api/status').then(response => response.json()).then(json => {
      //setStatus(json.status);
      setStatus('RUNNING');
      setLoading(false);
    });
  };

  const getButtonText = (status) => {
    switch (status) {
      case 'RUNNING':
        return 'Stop Server';
      case 'STOPPED':
        return 'Start Server';
      default:
        return 'Wait...';
    }
  }

  const isButtonDisabled = (status) => status !== 'RUNNING' && status !== 'STOPPED';

  const handleClick = () => {
    alert('This page is retired. The server is always running now');
    return;
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
    <ThemeProvider theme={theme}>
      <CssBaseline/>
	  <div>
	      The server is always on now. This page is retired!
	  </div>
      <Box
        sx={{
          marginTop: 40,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}>
        <StatusIcon status={status}/>
        <Button onClick={handleClick} disabled={buttonDisabled || loading} variant="contained" style={{marginTop: 20}}>
          {buttonText}
        </Button>
        <Button onClick={refresh} variant="contained" style={{marginTop: 20}}>Refresh</Button>
      </Box>
  </ThemeProvider>
  );
}

export default App;
