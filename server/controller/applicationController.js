const express = require('express');
const router = express.Router();
const applicationService = require('../service/applicationService');

router.get('/', async (req, res) => res.json(await applicationService.getAllApplications()));
router.get('/:id', async (req, res) => res.json(await applicationService.getApplicationById(req.params.id)));
router.get('/user/:userId', async (req, res) => res.json(await applicationService.getApplicationsByUser(req.params.userId)));
router.get('/job/:jobId', async (req, res) => res.json(await applicationService.getApplicationsByJob(req.params.jobId)));
router.post('/', async (req, res) => res.json(await applicationService.createApplication(req.body)));
router.patch('/:id/status', async (req, res) => res.json(await applicationService.updateApplicationStatus(req.params.id, req.body.status)));
router.delete('/:id', async (req, res) => res.json(await applicationService.deleteApplication(req.params.id)));

module.exports = router;
